package com.mychef.mychef


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.FileProvider
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.mychef.mychef.databinding.ListFragCalendarDayBinding
import com.mychef.mychef.databinding.ListFragCalendarHeaderBinding
import com.mychef.mychef.databinding.ListFragRecipeItemViewBinding
import com.mychef.mychef.databinding.FragmentListBinding
import id.jasoet.funpdf.HtmlToPdf
import id.jasoet.funpdf.PageOrientation
import java.io.File
import java.nio.file.Paths
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

data class Recipe(val id: String, val ingredient: String, val date: LocalDate)

class ListFragmentAdapter(val onClick: (Recipe) -> Unit) :
    RecyclerView.Adapter<ListFragmentAdapter.ListFragRecipesViewHolder>() {

    val recipes = mutableListOf<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFragRecipesViewHolder {
        return ListFragRecipesViewHolder(
            ListFragRecipeItemViewBinding.inflate(parent.context.layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ListFragRecipesViewHolder, position: Int) {
        viewHolder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    inner class ListFragRecipesViewHolder(private val binding: ListFragRecipeItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClick(recipes[bindingAdapterPosition])
            }
        }

        fun bind(recipe: Recipe) {
            binding.itemRecipeText.text = recipe.ingredient
        }
    }
}

class ListFragment : Fragment(R.layout.fragment_list) {

    private val ingredientAdapter = ListFragmentAdapter {
        AlertDialog.Builder(requireContext())
            .setMessage("Delete this ingredient?")
            .setPositiveButton("Delete") { _, _ ->
                deleteRecipe(it)
            }
            .setNegativeButton("Close", null)
            .show()
    }

    private val inputDialog by lazy {
        val editText = AppCompatEditText(requireContext())
        val layout = FrameLayout(requireContext()).apply {
            // Setting the padding on the EditText only pads the input area
            // not the entire EditText so we wrap it in a FrameLayout.
            val padding = dpToPx(20, requireContext())
            setPadding(padding, padding, padding, padding)
            addView(editText, FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT))
        }
        AlertDialog.Builder(requireContext())
            .setTitle("Enter ingredient name")
            .setView(layout)
            .setPositiveButton("Save") { _, _ ->
                saveRecipe(editText.text.toString())
                // Prepare EditText for reuse.
                editText.setText("")
            }
            .setNegativeButton("Close", null)
            .create()
            .apply {
                setOnShowListener {
                    // Show the keyboard
                    editText.requestFocus()
                    context.inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
                }
                setOnDismissListener {
                    // Hide the keyboard
                    context.inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                }
            }
    }

    private var selectedDate: LocalDate? = null
    private val today = LocalDate.now()

    private val titleSameYearFormatter = DateTimeFormatter.ofPattern("MMMM")
    private val titleFormatter = DateTimeFormatter.ofPattern("MMM yyyy")
    private val selectionFormatter = DateTimeFormatter.ofPattern("d MMM yyyy")
    private val recipes = mutableMapOf<LocalDate, List<Recipe>>()

    private lateinit var binding: FragmentListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        binding.listFragRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = ingredientAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }

        val daysOfWeek = daysOfWeekFromLocale()
        val currentMonth = YearMonth.now()
        binding.listFragCalendar.apply {
            setup(currentMonth.minusMonths(10), currentMonth.plusMonths(10), daysOfWeek.first())
            scrollToMonth(currentMonth)
        }

        if (savedInstanceState == null) {
            binding.listFragCalendar.post {
                // Show today's ingredients initially.
                selectDate(today)
            }
        }

        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay // Will be set when this container is bound.
            val binding = ListFragCalendarDayBinding.bind(view)

            init {
                view.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH) {
                        selectDate(day.date)
                    }
                }
            }
        }
        binding.listFragCalendar.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                val textView = container.binding.listFragDayText
                val dotView = container.binding.listFragDotView

                textView.text = day.date.dayOfMonth.toString()

                if (day.owner == DayOwner.THIS_MONTH) {
                    textView.makeVisible()
                    when (day.date) {
                        today -> {
                            textView.setTextColorRes(R.color.list_frag_white)
                            textView.setBackgroundResource(R.drawable.list_frag_today_bg)
                            dotView.makeInVisible()
                        }
                        selectedDate -> {
                            textView.setTextColorRes(R.color.list_frag_blue)
                            textView.setBackgroundResource(R.drawable.list_frag_selected_bg)
                            dotView.makeInVisible()
                        }
                        else -> {
                            textView.setTextColorRes(R.color.list_frag_black)
                            textView.background = null
                            dotView.isVisible = recipes[day.date].orEmpty().isNotEmpty()
                        }
                    }
                } else {
                    textView.makeInVisible()
                    dotView.makeInVisible()
                }
            }
        }

        binding.listFragCalendar.monthScrollListener = {
            // Select the first day of the month when
            // we scroll to a new month.
            selectDate(it.yearMonth.atDay(1))
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val legendLayout = ListFragCalendarHeaderBinding.bind(view).legendLayout.root
        }
        binding.listFragCalendar.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthViewContainer> {
            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                // Setup each header day text if we have not done that already.
                if (container.legendLayout.tag == null) {
                    container.legendLayout.tag = month.yearMonth
                    container.legendLayout.children.map { it as TextView }.forEachIndexed { index, tv ->
                        tv.text = daysOfWeek[index].name.first().toString()
                        tv.setTextColorRes(R.color.list_frag_black)
                    }
                }
            }
        }

        binding.listFragAddButton.setOnClickListener {
            inputDialog.show()
        }
    }

    private fun selectDate(date: LocalDate) {
        if (selectedDate != date) {
            val oldDate = selectedDate
            selectedDate = date
            oldDate?.let { binding.listFragCalendar.notifyDateChanged(it) }
            binding.listFragCalendar.notifyDateChanged(date)
            updateAdapterForDate(date)
        }
    }

    private fun saveRecipe(text: String) {
        if (text.isBlank()) {
            Toast.makeText(requireContext(), "No Text", Toast.LENGTH_LONG).show()
        } else {
            selectedDate?.let {
                recipes[it] =
                    recipes[it].orEmpty().plus(Recipe(UUID.randomUUID().toString(), text, it))
                updateAdapterForDate(it)
            }
        }
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun generatePdfShare() {
//        val pdf by lazy {
//            HtmlToPdf(executable = "/usr/bin/wkhtmltopdf") {
//                orientation(PageOrientation.LANDSCAPE)
//                pageSize("Letter")
//                marginTop("1in")
//                marginBottom("1in")
//                marginLeft("1in")
//                marginRight("1in")
//            }
//        }
//        val htmlString = "<html><body><h1>Hello World</h1></body></html>"
//
//        val outputFile = Paths.get(context?.filesDir?.parentFile?.path)
//        val inputStream = pdf.convert(input = htmlString,output = outputFile) // will always return null if output is redirecte
//        val shareIntent = Intent().apply {
//            this.action = Intent.ACTION_SEND
//            this.putExtra(Intent.EXTRA_STREAM,Uri.fromFile())
//            this.type = "application/pdf"
//
//        }
//    }

    private fun deleteRecipe(recipe: Recipe) {
        val date = recipe.date
        recipes[date] = recipes[date].orEmpty().minus(recipe)
        updateAdapterForDate(date)
    }

    private fun updateAdapterForDate(date: LocalDate) {
        ingredientAdapter.apply {
            recipes.clear()
            recipes.addAll(this@ListFragment.recipes[date].orEmpty())
            notifyDataSetChanged()
        }
        binding.listFragSelectedDateText.text = selectionFormatter.format(date)
    }

}