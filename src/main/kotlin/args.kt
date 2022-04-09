import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

/**
 * Command-line arguments description
 */
class Args(parser: ArgParser) {
    val i by parser.flagging(
        "-i",
        help = "the letters' case isn't taken into account"
    )
    val s by parser.storing(
        "-s",
        help = "first N symbols will be ignored"
    ) { toInt()}
        .default<Int>(0)

    val u by parser.flagging(
        "-u",
        help = "only uniq strings will be displayed"
    )

    val c by parser.flagging(
        "-c",
        help = "the number of merged strings that will be displayed before each"
    )

    val input by parser.positional("Input file name. If not specified will be read from the console")
        .default<String?>(null)

    val output by parser.storing("-o",
        help = "Output file name. If not specified will be displayed on the console")
        .default<String?>(null)
}