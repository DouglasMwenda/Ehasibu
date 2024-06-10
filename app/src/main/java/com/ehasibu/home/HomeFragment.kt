package com.ehasibu.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentHomeBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pieChart: PieChart
    private lateinit var barChart: BarChart
    private lateinit var lineChart: LineChart
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        pieChart = binding.pieChart

        val list: ArrayList<PieEntry> = ArrayList()
        list.add(PieEntry(100f, "100"))
        list.add(PieEntry(101f, "101"))
        list.add(PieEntry(102f, "102"))
        list.add(PieEntry(103f, "103"))
        list.add(PieEntry(104f, "104"))


        val pieDataSet = PieDataSet(list, "List")

        val colors = mutableListOf<Int>()
        colors.add(ContextCompat.getColor(requireContext(), R.color.colorBlue))
        colors.add(ContextCompat.getColor(requireContext(), R.color.colorRed))
        colors.add(ContextCompat.getColor(requireContext(), R.color.colorYellow))
        colors.add(ContextCompat.getColor(requireContext(), R.color.colorGreen))
        colors.add(ContextCompat.getColor(requireContext(), R.color.colorMaroon))

        pieDataSet.colors = colors
        pieDataSet.valueTextColor = Color.BLACK
        pieDataSet.valueTextSize = 15f
        val pieData = PieData(pieDataSet)
        pieChart.data = pieData
        pieChart.description.text = "Sales Per Item"
        pieChart.centerText = "List"
        pieChart.animateY(2000)


        barChart = binding.BarChart

        val barList: ArrayList<BarEntry> = ArrayList()
        barList.add(BarEntry(100f, 100f))
        barList.add(BarEntry(101f, 101f))
        barList.add(BarEntry(102f, 102f))
        barList.add(BarEntry(103f, 103f))
        barList.add(BarEntry(104f, 104f))

        val barDataSet = BarDataSet(barList, "List")

        val barChartcolors = mutableListOf<Int>()
        colors.add(ContextCompat.getColor(requireContext(), R.color.colorBlue))
        colors.add(ContextCompat.getColor(requireContext(), R.color.colorRed))
        colors.add(ContextCompat.getColor(requireContext(), R.color.colorYellow))
        colors.add(ContextCompat.getColor(requireContext(), R.color.colorGreen))
        colors.add(ContextCompat.getColor(requireContext(), R.color.colorMaroon))

        barDataSet.colors = barChartcolors
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 15f
        val barData = BarData(barDataSet)
        barChart.data = barData
        barChart.setFitBars(true)
        barChart.description.text = "Bar Chart"
        barChart.animateY(2000)

        lineChart = binding.linechart

        val entries = arrayListOf<Entry>()
        entries.add(Entry(0f, 1f))
        entries.add(Entry(1f, 2f))
        entries.add(Entry(2f, 0f))
        entries.add(Entry(3f, 4f))
        entries.add(Entry(4f, 3f))

        val dataSet = LineDataSet(entries, "Label")
        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.invalidate()

        val years = arrayOf("2018", "2019", "2020", "2021", "2022")

        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.valueFormatter = YearValueFormatter(years)


        val leftAxis = lineChart.axisLeft
        leftAxis.setDrawGridLines(false)

        val rightAxis = lineChart.axisRight
        rightAxis.isEnabled = false

        lineChart.description.isEnabled = false
        lineChart.legend.isEnabled = true

        return  binding.root
    }


    class YearValueFormatter(private val years: Array<String>) : ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            val index = value.toInt()
            return if (index >= 0 && index < years.size) {
                years[index]
            } else {
                ""
            }
        }
    }

}