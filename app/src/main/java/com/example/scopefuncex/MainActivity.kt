package com.example.scopefuncex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		var list = mutableListOf("ab", "cd", "ef")

		for(i in 0 until list.size ){
			Log.d("arrays", "${list[i]}")
		}

		list.run {
			val listSize = size
			val listCount = count()
			// run 영역 함수이므로 this.을 생략할 수 있음
			Log.d("ScopeTest", "size : ${listSize}, run count : ${listCount}")
			// size : 3, count : 3
			Log.d("ScopeTest", "list : ${list}")
			// list : [ab, cd, ef]
		}
		
		list.let {  aa ->		// it을 다른 명칭(aa)으로 변경하여 작업 가능
			val listSize = aa.size
			val listCount = aa.count()
			// let 영역이므로 it을 사용해야 하나 'aa'로 변경했으므로 aa 으로 작업
			Log.d("ScopeTest", "size : ${listSize}, let count : ${listCount}")
			// size : 3, count : 3
			Log.d("ScopeTest", "list : ${list}")
			// list : [ab, cd, ef]
		}

		//this를 리턴하는 함수들(apply, also)
		val objApply = list.apply{
			// list에 "apply"라는 문자열 추가
			list.add(list.size, "apply")
		}
		// 출력
		Log.d("ScopeTest", "list : ${list}")

		val objApply1 = list.also{
			// list에 "apply"라는 문자열 추가
			list.add(list.size, "also")
		}

		Log.d("ScopeTest", "list : ${list}")

		val objRun = list.run{
			add("run")	
			count()	// 마지막 명령으로 run영역 함수의 리턴값이 됨
		}	// run 영역 함수의 리턴값을 받는 objRun에는 count가 돌아감
		Log.d("ScopeTest", "list : ${objRun}")

		val objWith = with(list){
			add("with")
			count()	// 마지막 명령으로 with영역 함수의 리턴값이 됨
		}	// run 영역 함수의 리턴값을 받는 objWith에는 count가 돌아감
		Log.d("ScopeTest", "list : ${objWith}")

		val circle = ClsCircle(10.4)
		Log.d("ClsCircle", "list : ${circle.getArea()}")
		Log.d("ClsCircle", "list : ${circle.getPreri()}")

		// 영역함수를 이용하여 ClsCircle클래스의 rad 프로퍼티의 값을 변수 r에 저장 후 출력
		circle.run {
			var r = rad
			Log.d("ClsCircle", "list : ${getArea()}")
			Log.d("ClsCircle", "list : ${getPreri()}")
		}


	}
}

// 반지름을 입력받아 원의 넓이와 둘레를 리턴하는 함수를 가진 ClsCircle 클래스 작성

class ClsCircle(val rad : Double) {
	fun getArea() : Double{
		return rad * rad * 3.14
	}
	fun getPreri() : Double{
		return (rad * 2) * 3.14
	}
}