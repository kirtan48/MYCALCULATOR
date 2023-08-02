package com.example.mycalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var input: TextView?=null
     private var lastNumeric = false
   private var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
        input=findViewById(R.id.input)


    }
    fun onDigit(view : View){
        //Toast.makeText(this,"button clicked",Toast.LENGTH_LONG).show()
        input?.append((view as Button).text)
        lastNumeric=true
        lastDot=false


    }
    fun onClear(view:View){
        input?.text=""
        lastNumeric=false
        lastDot=false

    }
    fun onDot(view:View){
        if(lastNumeric && !lastDot){
            input?.append(".")
            lastNumeric=false
            lastDot=true
        }
    }
    fun onOperator(view:View){
        input?.text?.let{
            var x=""
            var temp=it.toString()
            //Toast.makeText(this,"$lastNumeric it${it==x}it ${(view as Button).text}",Toast.LENGTH_LONG).show()
           // Toast.makeText(this,"${it::class.simpleName}   ${x::class.simpleName} $lastNumeric it${it==x}it ${(view as Button).text}",Toast.LENGTH_LONG).show()


            if(lastNumeric && !isOperator(it.toString())){
               // Toast.makeText(this,"button clicked 2",Toast.LENGTH_LONG).show()
                input?.append((view as Button).text)
                lastNumeric= false
                lastDot=false
            }
            else if(temp==x && (view as Button).text=="-"  ){

                input?.append((view).text)
                //Toast.makeText(this,"button clicked",Toast.LENGTH_LONG).show()

            }
        }
    }

    fun onBack(view:View){
        var value=input?.text.toString()
        if(value!=""){
            input?.text=value.substring(0,value.length-1)
           if(value[value.length-1].toString()=="."){
               lastDot=false
               lastNumeric=true
           }



        }
        else{
            input?.text=value
        }
    }
    @SuppressLint("SetTextI18n")
    fun onEqual(view:View){
        var value=input?.text.toString()
        var prefix=""
        if(lastNumeric){
            try {
                if (value.startsWith("-")) {
                    value = value.substring((1))
                    prefix = "-"
                }

                if (value.contains("-")){
                val splitValue = value.split("-")
                if (prefix == "-") {
                    input?.text = isDecimal((-splitValue[0].toDouble()-splitValue[1].toDouble()).toString())
                } else {
                    input?.text = isDecimal((splitValue[0].toDouble()-splitValue[1].toDouble()).toString())
                }
                lastNumeric = true
                lastDot = true
            }
                else if (value.contains("+")){
                    val splitValue = value.split("+")
                    if (prefix == "-") {
                        input?.text = isDecimal((-splitValue[0].toDouble()+splitValue[1].toDouble()).toString())
                    } else {
                        input?.text = isDecimal((splitValue[0].toDouble()+splitValue[1].toDouble()).toString())
                    }
                    lastNumeric = true
                    lastDot = true
                }
                else if (value.contains("*")){
                    val splitValue = value.split("*")
                    if (prefix == "-") {
                        input?.text = isDecimal((-splitValue[0].toDouble()*splitValue[1].toDouble()).toString())
                    } else {
                        input?.text = isDecimal((splitValue[0].toDouble()*splitValue[1].toDouble()).toString())
                    }
                    lastNumeric = true
                    lastDot = true
                }
                else if (value.contains("/")){
                    val splitValue = value.split("/")
                    if (prefix == "-") {
                        input?.text = isDecimal((-splitValue[0].toDouble()/splitValue[1].toDouble()).toString())
                    } else {
                        input?.text = isDecimal((splitValue[0].toDouble()/splitValue[1].toDouble()).toString())
                    }
                    lastNumeric = true
                    lastDot = true
                }

            }catch (e :ArithmeticException){
                print(e)
            }
        }
    }
    private fun isDecimal(value:String):String{
        if(value.endsWith(".0")){
            return value.substring(0,value.length-2)
        }
        else{
            return value
        }
    }
    private fun isOperator(value : String):Boolean{
        val temp=value.substring(1).contains("-")

        return if(value.startsWith("-") && !temp){
            false
        }else{

            value.contains("/")
                    || value.contains("*")
                    || value.contains("-")
                    || value.contains("+")


        }
    }
}