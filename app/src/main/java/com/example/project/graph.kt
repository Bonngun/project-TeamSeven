package com.example.project

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate

/**
 * A simple [Fragment] subclass.
 */
class graph : Fragment() {

    lateinit var Bar_id : BarChart
    lateinit var Pie_id : PieChart
    lateinit var Line_id : LineChart

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_graph, container, false)
        // Inflate the layout for this fragment

        Pie_id = view.findViewById(R.id.pie_id)

//        Bar_Chart(Bar_id)
        Pie_Chart(Pie_id)
        //Line_Chart(Line_id)

        return view

    }
    fun Pie_Chart( chart: PieChart){

        //ปิด Description
        chart.description.isEnabled = false

        //สุ่มข้อมูล 5 อัน
        val listStudent = Student.getSampleStudentData(5)

        val entries: ArrayList<PieEntry> = ArrayList()
        for (student in listStudent) {
            entries.add(PieEntry(student.score, student.name))
        }

        val dataset = PieDataSet(entries, "Student")

        //กำหนดให้มีช่องว่างตรงกลางได้
        dataset.selectionShift = 10f

//กำหนด Size จำนสนข้อมูลที่แสดง
        dataset.valueTextSize = 10f

        //ตั้งค่า color
        dataset.setColors(*ColorTemplate.COLORFUL_COLORS) // set the color

        //เซ้ทช่องว่างความห่างของข้อมูล
        dataset.setSliceSpace(3f)

        //กำหนดข้อมูล
        val data = PieData(dataset)
        chart.setData(data)

        //กำหนดให้มีช่องว่างตรงกลางได้
        chart.setHoleRadius(30F)
        chart.setTransparentCircleRadius(40F)

        //กำหนด animation
        chart.animateY(3000)

        //อาตัวหนังสือออกมาไว้ข้างนอกตัวแผนภูมิ
        //X คือ ชื่อข้อมูล
        //Y คือ ค่าของข้อมูล
//        dataset.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE)
        dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE)

        //เส้นที่โยงออกมา
        dataset.setValueLinePart1Length(0.5f)
        dataset.setValueLinePart2Length(0.5f)

        //กำหนดให้แสดงเป็น %
        chart.setUsePercentValues(true)
        dataset.setValueFormatter(PercentFormatter(chart))

        // entry label styling
        chart.setEntryLabelColor(Color.WHITE)

        //ข้อความตรงกลางแผนภูมิ
        chart.setCenterText("My Android");
        chart.setCenterTextSize(5F)

    }


}
