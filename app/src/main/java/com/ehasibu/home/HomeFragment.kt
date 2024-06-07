package com.ehasibu.home

import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentHomeBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pieChart: PieChart
    private lateinit var barChart: BarChart
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        pieChart = binding.pieChart

        val list: ArrayList<PieEntry> = ArrayList()
        list.add(PieEntry(100f,"100"))
        list.add(PieEntry(101f,"101"))
        list.add(PieEntry(102f,"102"))
        list.add(PieEntry(103f,"103"))
        list.add(PieEntry(104f,"104"))


        val pieDataSet= PieDataSet(list,"List")

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
        pieChart.description.text= "Sales Per Item"
        pieChart.centerText = "List"
        pieChart.animateY(2000)


        barChart = binding.BarChart

        val barList: ArrayList<BarEntry> = ArrayList()
        barList.add(BarEntry(100f,100f))
        barList.add(BarEntry(101f,101f))
        barList.add(BarEntry(102f,102f))
        barList.add(BarEntry(103f,103f))
        barList.add(BarEntry(104f,104f))

        val barDataSet= BarDataSet(barList,"List")

        val barChartcolors = mutableListOf<Int>()
        colors.add(ContextCompat.getColor(requireContext(), R.color.colorBlue))
        colors.add(ContextCompat.getColor(requireContext(), R.color.colorRed))
        colors.add(ContextCompat.getColor(requireContext(), R.color.colorYellow))
        colors.add(ContextCompat.getColor(requireContext(), R.color.colorGreen))
        colors.add(ContextCompat.getColor(requireContext(), R.color.colorMaroon))

        barDataSet.colors= barChartcolors
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 15f
        val barData = BarData(barDataSet)
        barChart.data = barData
        barChart.setFitBars(true)
        barChart.description.text= "Bar Chart"
        barChart.animateY(2000)

        return  binding.root
    }
}