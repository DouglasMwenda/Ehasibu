package com.example.ehasibu.home

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
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pieChart: PieChart
    private lateinit var barChart: BarChart
    private lateinit var lineChart: LineChart
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupPieChart()
        setupBarChart()
        setupLineChart()

        return binding.root
    }

    private fun setupPieChart() {
        pieChart = binding.pieChart

        val list = listOf(
            PieEntry(100f, "100"),
            PieEntry(101f, "101"),
            PieEntry(102f, "102"),
            PieEntry(103f, "103"),
            PieEntry(104f, "104")
        )

        val pieDataSet = PieDataSet(list, "List").apply {
            colors = listOf(
                ContextCompat.getColor(requireContext(), R.color.colorBlue),
                ContextCompat.getColor(requireContext(), R.color.colorRed),
                ContextCompat.getColor(requireContext(), R.color.colorYellow),
                ContextCompat.getColor(requireContext(), R.color.colorGreen),
                ContextCompat.getColor(requireContext(), R.color.colorMaroon)
            )
            valueTextColor = Color.BLACK
            valueTextSize = 15f
        }

        pieChart.apply {
            data = PieData(pieDataSet)
            description.text = "Sales Per Item"
            centerText = "List"
            animateY(2000)
        }
    }

    private fun setupBarChart() {
        barChart = binding.BarChart

        val barList = listOf(
            BarEntry(0f, 100f),
            BarEntry(1f, 101f),
            BarEntry(2f, 102f),
            BarEntry(3f, 103f),
            BarEntry(4f, 104f)
        )

        val barDataSet = BarDataSet(barList, "List").apply {
            colors = listOf(
                ContextCompat.getColor(requireContext(), R.color.colorBlue),
                ContextCompat.getColor(requireContext(), R.color.colorRed),
                ContextCompat.getColor(requireContext(), R.color.colorYellow),
                ContextCompat.getColor(requireContext(), R.color.colorGreen),
                ContextCompat.getColor(requireContext(), R.color.colorMaroon)
            )
            valueTextColor = Color.BLACK
            valueTextSize = 15f
        }

        barChart.apply {
            data = BarData(barDataSet)
            setFitBars(true)
            description.text = "Bar Chart"
            animateY(2000)
        }
    }

    private fun setupLineChart() {
        lineChart = binding.linechart

        val entries = listOf(
            Entry(0f, 1f),
            Entry(1f, 2f),
            Entry(2f, 0f),
            Entry(3f, 4f),
            Entry(4f, 3f)
        )

        val lineDataSet = LineDataSet(entries, "Label")
        val lineData = LineData(lineDataSet)

        lineChart.apply {
            data = lineData
            invalidate()

            val years = arrayOf("2018", "2019", "2020", "2021", "2022")

            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                granularity = 1f
                valueFormatter = YearValueFormatter(years)
            }

            axisLeft.setDrawGridLines(false)
            axisRight.isEnabled = false

            description.isEnabled = false
            legend.isEnabled = true
        }
    }

    class YearValueFormatter(private val years: Array<String>) : ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            return years.getOrElse(value.toInt()) { "" }
        }
    }
}
