package stslex.datamark

import android.content.ClipboardManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import stslex.datamark.databinding.ActivityMainBinding
import stslex.datamark.util.Resources.clipboardManager
import stslex.datamark.util.Resources.labelCopy

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        labelCopy = getString(R.string.label_copy)
        clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}