import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody
import kotlin.system.exitProcess
import uniq.UniqStrings

fun main(args: Array<String>) {
    mainBody {
        ArgParser(args, helpFormatter = null).parseInto(::Args).run {
            UniqStrings(i,u, c, s, input, output).output()
        }
    }
    exitProcess(0)
}


