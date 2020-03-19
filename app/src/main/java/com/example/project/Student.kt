package com.example.project

class Student(var name: String, var score: Float) {
    companion object {
        fun getSampleStudentData(size: Int): ArrayList<Student> {
            val student: ArrayList<Student> = ArrayList()
                student.add(Student("พัสกร", Math.random().toFloat() * 100))
                student.add(Student("วีรพงศ์", Math.random().toFloat() * 100))
                student.add(Student("กัลยรัตน์", Math.random().toFloat() * 100))
                student.add(Student("ชลลดา", Math.random().toFloat() * 100))
                student.add(Student("ลลิตา", Math.random().toFloat() * 100))
                student.add(Student("กัณณภัต", Math.random().toFloat() * 100))
                student.add(Student("กิตติญา", Math.random().toFloat() * 100))
                student.add(Student("ธิมาภรณ์", Math.random().toFloat() * 100))
                student.add(Student("พฤกษ์", Math.random().toFloat() * 100))
            return student
        }
    }

}