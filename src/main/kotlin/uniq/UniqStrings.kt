package uniq

import java.io.File
import java.io.FileNotFoundException
import java.util.*

class UniqStrings(
    private val i: Boolean,
    private val u: Boolean,
    private val c: Boolean,
    private val s: Int,
    private val input: String,
    private val output: String) {


    //original text, that is taken from file or console input. (packed in a list)
    private val inputText = input()
    //processed text, without any repeated strings in a row. (packed in a list)
    private val mergedText = uniqStrings()

    //function to get "text" variable
    private fun input(): MutableList<String> {
        val text = mutableListOf<String>()
        //taking text from console input
        if (input.isEmpty()) {
            val reader = Scanner(System.`in`)
            println("Enter a String to check or press \"ctrl(cmd) + Z\"")
            //var string = reader.nextLine()
            while (reader.hasNext()) {
                text.add(reader.nextLine())
                //string = reader.nextLine()

            }
            //taking text from the file
        } else {
            if (!File(input).exists()) {
                throw FileNotFoundException("File ${File(input).absolutePath} does not exist!")
            }
            return File(input).readLines().toMutableList()

        }
        return text
    }

    //function to get "mergedText" variable
    private fun uniqStrings (): MutableList<String> {

        var keyString = inputText.first()
        var counter = 1 //special variable that'll count the number of repeated strings in a row
        val text = mutableListOf<String>()

        for (k in 1 .. inputText.lastIndex) {

            //value that is true when current line is similar to keyString and false if not
            //also checks "s" and "i" flags
            val similar = inputText[k].drop(s).equals(keyString.drop(s) , ignoreCase = i)

            if (!similar) {

                if (counter > 1) {
                    if (u) keyString = ""
                    //adding "counter" to output string if "c" flag was indicated
                    addingStrings(counter, keyString, text)
                    keyString = inputText[k]
                    counter = 1
                //all checks above aren't obligatory if keyString didn't have "clones"
                } else {
                    addingStrings(counter, keyString, text)
                    //mergedText.add(keyString)
                    keyString = inputText[k]
                }
            //if current line equals keyString we just keep track of counter
            } else {
                counter+=1
            }
        }
        //final part to process last line
        if (counter == 1) {
            addingStrings(counter, keyString, text)
        } else {
            if (u) keyString = ""
            addingStrings(counter, keyString, text)
        }
        return text
    }

    //function to get final result of program
    fun output() {
        //processed text will be displayed on the screen
        if (output.isEmpty()) {
            for (line in mergedText) {
                println(line)
            }
        }
        //processed text will be added to the output file
        else {
            if (!File(output).exists()) {
                throw FileNotFoundException("File ${File(output).absolutePath} does not exist!")
            }
            val writer = File(output).bufferedWriter()
            for (line in mergedText) {
                writer.write(line)
                writer.newLine()
            }
            writer.close()
        }
    }

    private fun addingStrings(counter: Int, keyString: String, text: MutableList<String>){
        val emptyCheck = keyString.isEmpty()
        when (c) {
            true -> {
                if (emptyCheck) {
                    text.add("\"$counter\"")
                }
                else {
                    text.add("\"$counter\" $keyString")
                }
            }
            false -> {
                //we don't need to add empty string if "u" flag was indicated
                if (!emptyCheck) text.add(keyString)
            }
        }
    }
}


