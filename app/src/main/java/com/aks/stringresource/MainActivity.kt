package com.aks.stringresource

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.aks.stringresource.databinding.ActivityMainBinding
import java.time.format.TextStyle
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var totalLetterInALine = 0
    var totalNeedToDisplay = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.name.text = getString(R.string.message, "Puja")
        binding.name1.text = Html.fromHtml(
            getString(R.string.welcome_messages, "Anil", 2, "Thank you!"),
            FROM_HTML_MODE_LEGACY
        )
        binding.name2.text = getString(R.string.message, "Anil")

        val text = getString(R.string.welcome_messages2, "Anil", 20)
        val styledText: Spanned = Html.fromHtml(text, FROM_HTML_MODE_LEGACY)
        val songFound = resources.getQuantityString(R.plurals.numberOfSongsAvailable, 1, 10)
        binding.name3.text = styledText


        val spannableString = SpannableString("Hi Anil, How are you doing?")
        val foregroundColor = ForegroundColorSpan(Color.RED)
        spannableString.setSpan(foregroundColor, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        val fontSize = StyleSpan(Typeface.BOLD)
        spannableString.setSpan(fontSize, 3, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        binding.name4.text = spannableString


        val spannableStr = SpannableString("Click here")
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                Toast.makeText(this@MainActivity, "Hi, Anil", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
        }

        spannableStr.setSpan(
            clickableSpan,
            0,
            spannableStr.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.name5.text = spannableStr
        binding.name5.movementMethod = LinkMovementMethod.getInstance()


        val spannableText = SpannableStringBuilder(getString(R.string.expandable_text))
        val spannableTextLess =
            SpannableStringBuilder(getString(R.string.expandable_text).substring(0, 100))

        val clickableSpan1 = object : ClickableSpan() {
            override fun onClick(p0: View) {
                binding.name6.text = spannableTextLess
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
        }
        val clickableSpan2 = object : ClickableSpan() {
            override fun onClick(p0: View) {
                binding.name6.text = spannableText
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
        }

        val seeLessSpannable = SpannableString(" ..see less")
        seeLessSpannable.setSpan(
            clickableSpan1,
            0,
            seeLessSpannable.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        val seeMoreSpannable = SpannableString(" ..see more")
        seeMoreSpannable.setSpan(
            clickableSpan2,
            0,
            seeMoreSpannable.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )

        spannableText.append(seeLessSpannable)
        spannableTextLess.append(seeMoreSpannable)

        binding.name6.text = spannableTextLess
        binding.name7.movementMethod = LinkMovementMethod.getInstance()
        binding.name6.movementMethod = LinkMovementMethod.getInstance()



        Log.e("Text Count", ": ${binding.name6.text.count()}")

        //this one is recommended
        text7Operation()
    }

    private fun text7Operation() {
        binding.name7.maxLines = 2
        Log.e("Text Count 7", ": ${binding.name7.text.count()}")
        Log.e("Text Count 7", ": ${binding.name7.length()}")
        val paint = binding.name7.paint
        val wordWidth = paint.measureText("e", 0, 1)
        val screenWidth = resources.displayMetrics.widthPixels
        totalLetterInALine = (screenWidth / wordWidth).toInt()
        totalNeedToDisplay = (totalLetterInALine * 2) - 10
        Log.e("Total number of text", ": $totalLetterInALine")
        Log.e("Total number of text", ": $totalNeedToDisplay")


        val spannableTextMore: SpannableStringBuilder =
            if (getString(R.string.expandable_text).length > totalNeedToDisplay) {
                SpannableStringBuilder(
                    getString(R.string.expandable_text).substring(
                        0,
                        totalNeedToDisplay
                    )
                )
            } else {
                SpannableStringBuilder(getString(R.string.expandable_text))
            }
        val spannableTextLess = SpannableStringBuilder(getString(R.string.expandable_text))

        val clickableSpan1 = object : ClickableSpan() {
            override fun onClick(p0: View) {
                binding.name7.text = spannableTextMore
                binding.name7.maxLines = 2
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
        }
        val clickableSpan2 = object : ClickableSpan() {
            override fun onClick(p0: View) {
                binding.name7.text = spannableTextLess
                binding.name7.maxLines = Int.MAX_VALUE
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
        }
        val seeLessSpannable = SpannableString(" ..see less")
        seeLessSpannable.setSpan(
            clickableSpan1,
            0,
            seeLessSpannable.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        val seeMoreSpannable = SpannableString(" ..see more")
        seeMoreSpannable.setSpan(
            clickableSpan2,
            0,
            seeMoreSpannable.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )

        spannableTextMore.append(seeMoreSpannable)
        spannableTextLess.append(seeLessSpannable)


        binding.name7.text = spannableTextMore
    }
}