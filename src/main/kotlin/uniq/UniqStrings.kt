package uniq

import com.xenomachina.argparser.SystemExitException
import java.io.File
import java.util.*

class UniqStrings(
    private val i: Boolean,
    private val u: Boolean,
    private val c: Boolean,
    private val s: Int,
    private var input: String?,
    private val output: String?) {

    //original text, that is taken from file or console input. (packed in a list)
    private val text = input()
    //processed text, without any repeated strings in a row. (packed in a list)
    private val mergedText = uniqStrings()

    //function to get "text" variable
    private fun input(): MutableList<String> {
        val text = mutableListOf<String>()

        //taking text from console input
        if (input.equals(null)) {
            println("Write string to check")
            val reader = Scanner(System.`in`)
            println("Enter a String to check or \"stopLine\" to stop entering")
            var string = reader.nextLine()
            while (!string.equals("stopLine") && reader.hasNext()) {
                text.add(string)
                string = reader.nextLine()
                if (string.equals("stopLine")) break
            }
            //taking text from the file
        } else {
            if (!File(input).exists()) {
                throw SystemExitException("File ${File(input).absolutePath} does not exist!", 1)
            }
            val reader = File(input).readLines()
            for (line in reader) {
                text.add(line)
            }

        }
        return text
    }

    //function to get "mergedText" variable
    private fun uniqStrings (): MutableList<String> {

        var keyString = text.first()
        var counter = 1 //special variable that'll count the number of repeated strings in a row
        val mergedText = mutableListOf<String>()

        for (k in 1 .. text.lastIndex) {

            //value that is true when current line is similar to keyString and false if not
            //also checks "s" and "i" flags
            val similar = text[k].drop(s).equals(keyString.drop(s) , ignoreCase = i)

            if (!similar) {

                if (counter > 1) {
                    if (u) keyString = ""
                    //adding "counter" to output string if "c" flag was indicated
                    when (c) {
                        true -> {
                            mergedText.add("\"$counter\" $keyString")
                        }
                        false -> {
                            //we don't to add empty string if "u" flag was indicated
                            if (keyString.isNotEmpty()) mergedText.add(keyString)
                        }
                    }
                    keyString = text[k]
                    counter = 1
                //all checks above aren't obligatory if keyString didn't have "clones"
                } else {
                    mergedText.add(keyString)
                    keyString = text[k]
                }
            //if current line equals keyString we just keep track of counter
            } else {
                counter+=1
            }
        }
        //final part to process last line
        if (counter == 1) {
            mergedText.add(keyString)
        } else {
            if (u) keyString = ""
            when (c) {
                true -> {
                    mergedText.add("\"$counter\" $keyString")
                }
                false -> {
                    if (keyString.isNotEmpty()) mergedText.add(keyString)
                }
            }
        }
        return mergedText
    }

    //function to get final result of program
    fun output() {
        //processed text will be displayed on the screen
        if (output.equals(null)) {
            for (line in mergedText) {
                println(line)
            }
        }
        //processed text will be added to the output file
        else {
            if (!File(input).exists()) {
                throw SystemExitException("File ${File(input).absolutePath} does not exist!", 1)
            }
            val writer = File(output).bufferedWriter()
            for (line in mergedText) {
                writer.write(line)
                writer.newLine()
            }
            writer.close()
        }
    }
}


