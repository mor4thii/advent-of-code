package com.fred.adventofcode

fun main() {
    val input =
        """cdhccdbdggfjjgssjzjzggjnjpnpbbzbnzzflfjfnfrrpvrvbrvvrvggvlvnnbrnrcncsnndbndbnndbdndfdrdvrvvndvvbggnrrnbrnntffgttwzwnnmvmcvvhsstzzlnlwlttbzzpnpmnnjvjnntmnmfftwwrfwrwswmmfrrfrrgbrbffwvvshvhrhmhththbbmqbmqqlslhssrmmqdmmjtmtmjtmjtttnwnvwvqwqjjnbbbdbqbnbpnbnllglcglcgcdczdznnqhhfthtmtlldqlqrrmddrldlzdllvddjcddqfqbqsbqqnllwppqpqzzrbbdppzsppjdpdqpdqdfqfrrwbwrwwqcqcsqsvvpbvbbztzptzzpccdtdhdffvqvcvzzmwzwddjfdffplplqlvlmmmvggpmpvpddpbptplpvlplvpvvnrvnnbqqqjhhwfhwfhwhqhmmpphqpqvppfzpzjzddgzzwffjmjggwhwwnnmlmpmmhcmcpcrcddvzvpzzwnznfznffgdgvddvtvgvsvdsdbbjnjtntbnttgbbbvgvgrgrzrvzrzddlsddcndcnnfqnnmpmlppdlplzplzpzgzmzmddlvlnnbttbwwhbhdhfdfssjppmcpplpdddnpdnnljlwjljsjnjhnhvhvqqsffrbbdttjdjndjdwwsfspffnhfhhlvhvmmqjmmwzwszwwvdvpdvdbdtdsdtsshvvmtvmtmctclchccrllznzfffpjjvhhdmhhvphpghgsgmmhlhnlnmnlnslnlgngznnsqnqddllpwllmzmjmttptfpplglqlgglgqqptqqmvmtmjmddcchbblltslsvsmvmgghmmccnzcztczzmnmttrdrvvcvzvvzllbhllnldndbbqffbbgtgddbtdbbzttdptpccjnjppbllbzlblrlcllhrrhqhgqqbcqcvcdvvnnzfzvzttrptrrwmrmlrlddvttdbtdbdcdvccwlcwwhphmppwfppclpcllgqgnghhvfflfggrzrcchfhhrdhrdhdnhnmmhjjwqjjpmmwvmmdnmnzzqmqwwmtthtdhtthnnqhqdhqqndqqwffsbspbptpmmndnllsmmdhmhfhnhjhghshlslppbgpgngddlsljsjmmzqzhhswhssfzssfqqcmqcmqcmmqggjcjvvgssrccwddmpmwwdfdpdbdpdwdvvqfvfrvvvsbvbhvvmqqcjqqvzqzppncnhhqnnpgplpqqpjpbblpbbbshsthhvfhfmmqzmmznnvrvqrrwdrdlrlwlttzqttjvttqltqqnfqqqwjqwwqttfstftjffsqqnhhnsnqqhggbsgghfgglslmssqlqhlhthqhccdsspsnssshbbnmngnnhllwclcffqllsrszrssnqsqvqjvjcvcttqgqbqmmfqfsqfsqswwvcvffndnfdfvfcvvggsmsfmfwfpfwwzhznntgtlglmmlfmllwrlrwwhcchqchhznzjjcdjdbjjhcjcscwwlnnsgngqqtgqgngnwgnnhqnnhchmchhtchcnclcmccgffbmmzvvrnngwwvddzccnjntjtwjwwztwtmtddjddpsptpbpbvvbwwnlnmndmnmdnnclnnbsbddpfdfvvjtjqqtqqqzjzlzqllzzwwlppvfffpcffffprrncnnzsnznhhwvvqhqphpjjgqqvnnmdmqqglqlblgglrlsspscsjjpvpbpjjwccslsppdjpdjjwvjjmhjhtjjwqqbqjqzjqzqpqbbswwlssqzssbjjpjqjbbjcjpjspjssjjzhhhnjhnhbnhhwzhzwlcshqlqpzgggzmcwntcwmfgtrwwjdpnbdqqcgnzgbdrzdmpwgvtvqffqbpvjpjrcfswffllnvnwvhclpjcwqwgnwqwvwsfgflrgzzsswffwjdjgvdvlgmczcbthwbvhggwzwlzfmhvwvjpbpnhcczbgfhhgghsmjwnvnsvnvmqwstrgnncwbqgbqpgdngllcqnzgwswpgtwzgqzggnzsdgltrlqfctqfvlzdswccfpdtjbfnrbqsmpjclnplbmqbmvwbzzdflwbqrljvzjpcrmnqsmrpqlmfsgcmthqpwgwzvmrjnhqczljcpnzjbwzrhjrzmcqpmlbzhgmqrlzsjbjsvcmcngptzlrthwsrjrlmsrgjlzrvpzwmprwnpgvjtspsppfvwfwcvbnqcwwmzlbqthqmbnbmnsnzgsbbnqtrvhlzjhphclpjzrdblszrnftqgwwrhpznhjhgrncvsvrmtmmgssvzddjfrnrzhbrqrfffjvzrqdnrdbvjwgrvlcpbncfgczlwdggsjmwzhndcdbggjvwfljctjnsjwczwfdrfttbhnlswfdbpcnwpspdhnzwqbgdswwpccbpfpgmfmvvwpzbzqsbbjbfnhjpszcbnrdplmwtdjtpcsztdjcmczltnstzwlcdbtdhsdgsgtlvdfqggfmmrppjfrmtfwhpbjsppszjbhmthndqmvbmqcbtqsltwrcvlvblwspbgspjftwllzcmnsrvjpnstzrfmcflnhppsdfggwbzvnvlnjqlfvrlplnzvfrwvgcgqvnpfgtgchctvhcplclzmfpwgnfhqjgglfmsgpflqcpqmbbhwnvvdllcnhblpnndbdtmgvfbvvvlvzlrpfqmnvzbfrssjtlgcjtpfznshvdjrjnfshfcgvwcdbqlfsbhnzwmsgwhpbzttgfjsqgwvdmbdwjljhsndrbbzfrsqjhcbldzqpmtnfvnmzltjcrvrltwshnhqlnclmcnfpbzstsczlqmfmdftzfbcwqnhqppzfbzpbfjhmmtvtbmblmtshsbtjlvsqvmbmgstbbdmhprqmtpfdqqntmnlbmpsmwfgrvstjcllhwpcddnljdjvdrbwqmgrjnldpgnrhgqpzqrvwzsngrgnbpjnsffzjsbdptwnnfcqlscfhvggpfstsnqzcjbqqhgdpqsrlprcppgqmddpqpbnvgwtdqsbbgtvsqfrtqfsbdzhsztfmvwrrsjcbtcjgzrnhnpgldtwbwgmwbgmjjzsbbzlhgmlczrhjwtzrgwscmjvlstprldhglvftqzbtrmcwzgtjppbnjcdvjvcwvdbngnbrmjvvtnwdqfclbpgzcfnnnlnngtgmhsqsdmbjctjzjpbrwrhscqshmmwbtfnzjgsrjlnqqdsvdrjdzsdprphnfmwwcztqfcrjvnfhlvnqwbrfmcvhrbtgvcrqjjfcnzwmlfzzdcbbzvphhmsdltwjfdcgthpvszqzjdbfwrpvhbjqdhrscnvjhjvvcldnhgjclmzpbrrwnscgpcqrpdgsnjnwhctcdqgwqbrcszfzpmtdrhlftvwffdjrtznqrppqbdbwvzmtlpvsqqpcngjgfdrpngnspdwhhvlhqrtsphgqrlldggtrvqsprbfdmrpgcmqphdvjfmhlznpgtqlvtnllcdhzhhtjjlfvdlwhcrfmjmdjtmbllvsfgvmfqtqlmrlrjmqptszvjdpzhphppljnpjdjpwlrclssgdnstchhwhpflmlrtdqvqbbljrmnflrltzpqmgqfrczvfzrpfsrwsgpljvjfjdjdvjchcdmmtjgghqspwzdtwqgtvmnrrbfbgnhcrvnzznrdlqmgmdwmpwzlqdjtvpszwnjtjtmjqvfwvftlhgpvgzswpbvbllfcwpjnsmbhzrdpdzjsrpnhphdcqjmzvvhrjcwhgwjwcshqwzpbpmfnjjvqcjrqmvsrdrtdvfhwhrbpvrqrsfzflslqtdrtcsggtzmpvbszdgttlvpwwltvpcwqmnwqtpcfzgsvsmncvpqqdrljfwtncplmjlpfcnqmcctwzhrbmrfwvsrjsbnhjrjmrnbmmnnhsvlltwzzhsgwppnlmljgpcsmpchdjdzpgvrtwsfzffhnlbfmrldzbshvpqhnfzpwnvczgfvhbntcpztwqlfgtsmdhvcrgjhvqrhbpvbpzcpbgzrcfjztbnfjptbzfpztwprwf"""

    println(input.findMarker(4))  // 6-1
    println(input.findMarker(14))  // 6-2
}

private fun String.findMarker(length: Int) = this
    .windowed(length)
    .indexOfFirst { window -> window.groupBy { it }.size == length } + length
