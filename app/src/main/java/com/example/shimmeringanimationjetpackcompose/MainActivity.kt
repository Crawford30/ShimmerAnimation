package com.example.shimmeringanimationjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

import com.example.shimmeringanimationjetpackcompose.compoments.AnimatedShimmer
import com.example.shimmeringanimationjetpackcompose.ui.theme.ShimmeringAnimationJetPackComposeTheme
import de.charlex.compose.material3.HtmlText
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val textThree = """
                Kumuga ogwo, nja kugenda;
                Ndete ebibi byesisobolaa;
                Jangu ontukuze, jangu onsonyiwe;
                Neetaga, tusisinkanee.

                	CHORUS
                	Mukwano Yesu, netegeze;
                	Okuwayo byona ebyange;
                	Nkwata omukono, onsembeze;
                	Neetaga tusisinkanee.

                Mumazi gano, echisa echiwonya;
                Chikulukuta nedembe;
                Njakugenda, kumuga ogwo;
                Neetaga tusisinkanee.

                Jangu otwegateko, kumuga ogwo;
                Ofune obulamu obutenkanika;
                Akuyita, akulinze;
                Yesu ayagala musisinkane.

                **********
                (ENGLISH TRANSLATION)
                60. TO THE RIVER

                To the river, I am going;
                Bringing sins I can not bear;
                Come and cleanse me, come forgive me;
                Lord I need to meet you there.

                	CHORUS
                	Precious Jesus, I am ready;
                	To surrender every care;
                	Take my hands now, lead me closer;
                	Lord I need to meet you there.

                In this waters, healing mercies;
                Flows with freedom from despair;
                I am going to the river;
                Lord I need to meet you there.

                Come and join us, to the river;
                come find life, beyond compare;
                He is calling, He is waiting;
                Jesus longs to meet you there.
            """.trimIndent()

            val htmlText = """
        <strong>ANNOTATED<br>
        Make this bold
        Using AnnotatedString we can pretty much automate
        </strong><br> <br>

        Using AnnotatedString we can pretty much automate the styling and annotation of our content.
        One of my recent releases is DailyTags library. It can parse markdown,
        some HTML and even custom markups! Using AnnotatedString it could both style
        In fact<br><br>

        <strong>
        Using AnnotatedString we can pretty much automate the styling and annotation of our content.
        One of my recent releases is DailyTags library. It can parse markdown.</strong>
    """.trimIndent()
            ShimmeringAnimationJetPackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.background)
                            .padding(16.dp)
                    ) {

                        HtmlText(
                            modifier = Modifier.padding(18.dp),
                            text = textThree,
                            fontFamily = FontFamily.Monospace
                        )

                    }
                }
            }
        }
    }
}


//@Composable
//fun MyFormattedText(lyric: String): String {
//    val formattedText = HtmlText(text = lyric)
//    return formattedText.toString()
//}


@Composable
fun StringGreeting() {
    HtmlText(text = "Hello <b>World</b>. This <i><strike>text</strike>sentence</i> is form<b>att<u>ed</u></b> in simple html. <a href=\"https://github.com/ch4rl3x/HtmlText\">HtmlText</a>")
}

@Composable
fun MultipleLinks() {
    HtmlText(text = "<a href=\"https://github.com/ch4rl3x/HtmlText\">HtmlText</a> by <a href=\"https://github.com/ch4rl3x\">ch4rl3x</a>")
}

@Composable
fun ColorTextBySpan() {
    HtmlText(text = "Hello <span style=\"color: #0000FF\">blue</span> world")
}

@Composable
fun ColorTextByFont() {
    HtmlText(text = "Hello <font color=\"red\">red</font> world")
}

@Composable
fun processElement(
    element: Element,
    regularText: AnnotatedString.Builder,
    boldText: AnnotatedString.Builder
) {
    for (child in element.children()) {
        when (child.tagName()) {
            "p" -> {
                // Add a line break before each paragraph
                if (regularText.length > 0) {
                    regularText.append("\n>")
                }
                // Process the paragraph content
                processElement(child, regularText, boldText)
            }
            "strong" -> {
                processElement(child, regularText, boldText.apply {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        // Process the bold content within the paragraph
                        append(child.ownText())
                    }
                })
            }
            else -> {
                // Process regular text
                regularText.append(child.ownText())
                processElement(child, regularText, boldText)
            }
        }
    }
}


//@Composable
//fun processElement(element: Element, regularText: AnnotatedString.Builder, boldText: AnnotatedString.Builder) {
//    var hasContent = false  // Track whether any content has been appended
//
//    for (child in element.children()) {
//        when (child.tagName()) {
//            "p" -> {
//                // Add a line break before each paragraph
//                if (hasContent) {
//                    regularText.append("\n")
//                }
//                // Process the paragraph content
//                processElement(child, regularText, boldText)
//            }
//            "strong" -> {
//                boldText.withStyle(
//                    style = SpanStyle(
//                        fontWeight = FontWeight.Bold
//                    )
//                ) {
//                    append(child.text())
//                }
//                hasContent = true
//            }
//            else -> {
//                regularText.append(child.ownText())
//                hasContent = true
//            }
//        }
//        processElement(child, regularText, boldText)
//    }
//}



