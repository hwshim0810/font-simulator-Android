package xyz.laziness.fontsimulator.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import uk.co.chrisjenx.calligraphy.TypefaceUtils


const val STANDARD_TEXT_SIZE: Int = 14

class MainActivity : AppCompatActivity() {

    private val fontBold by lazy { getString(R.string.font_bold) }
    private val fontDemiLight by lazy { getString(R.string.font_demilight) }
    private val fontMedium by lazy { getString(R.string.font_medium) }
    private val fontPathBold by lazy { getString(R.string.font_path_bold) }
    private val fontPathDemi by lazy { getString(R.string.font_path_demi) }
    private val fontPathMedium by lazy { getString(R.string.font_path_medium) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initFont()
        initUi()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun setSeekBarEvent() {
        fontSizeSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                val currentSize = (progress + STANDARD_TEXT_SIZE).toFloat()
                singleLineText.setTextSize(
                        TypedValue.COMPLEX_UNIT_SP, currentSize)
                multiLineText.setTextSize(
                        TypedValue.COMPLEX_UNIT_SP, currentSize)
            }

            override fun onStartTrackingTouch(sb: SeekBar?) {
            }

            override fun onStopTrackingTouch(sb: SeekBar?) {
            }
        })

        lineSpacingSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                val currentSpacing = progress.toFloat()
                singleLineText.setLineSpacing(currentSpacing, 1f)
                multiLineText.setLineSpacing(currentSpacing, 1f)
            }

            override fun onStartTrackingTouch(sb: SeekBar?) {
            }

            override fun onStopTrackingTouch(sb: SeekBar?) {
            }
        })

        letterSpacingSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                val currentSpacing = progress.toFloat()
                singleLineText.letterSpacing = currentSpacing
                multiLineText.letterSpacing = currentSpacing
            }

            override fun onStartTrackingTouch(sb: SeekBar?) {
            }

            override fun onStopTrackingTouch(sb: SeekBar?) {
            }
        })
    }

    private fun setEditTextEvent() {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(str: CharSequence?, start: Int, before: Int, count: Int) {
                singleLineText.text = str
                multiLineText.text = str
            }
        })
    }

    private fun setRadioButtonEvent() {
        fontGroup.setOnCheckedChangeListener {
            radioGroup: RadioGroup?, i: Int ->
                val checkedButton = radioGroup!!.findViewById<RadioButton>(i)

                when (checkedButton.text) {
                    fontBold -> setExampleTextFont(fontBold)
                    fontDemiLight -> setExampleTextFont(fontDemiLight)
                    fontMedium -> setExampleTextFont(fontMedium)
                }
        }
    }

    private fun initFont() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }

    private fun setExampleTextFont(type : String) {
        when (type) {
            fontBold -> {
                singleLineText.typeface = TypefaceUtils.load(assets, fontPathBold)
                multiLineText.typeface = TypefaceUtils.load(assets, fontPathBold)
            }

            fontDemiLight -> {
                singleLineText.typeface = TypefaceUtils.load(assets, fontPathDemi)
                multiLineText.typeface = TypefaceUtils.load(assets, fontPathDemi)
            }

            fontMedium -> {
                singleLineText.typeface = TypefaceUtils.load(assets, fontPathMedium)
                multiLineText.typeface = TypefaceUtils.load(assets, fontPathMedium)
            }
        }
    }

    private fun initUi() {
        setEditTextEvent()
        setSeekBarEvent()
        setRadioButtonEvent()
    }

}

