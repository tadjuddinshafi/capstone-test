package bangkit.android.capstone

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import bangkit.android.capstone.databinding.ActivityNoBinding
import bangkit.android.capstone.ui.detaill.ProductActivity
import java.io.File

class NoActivity : AppCompatActivity() {

    companion object {
        private const val FILE_NAME = "photo.jpg"
        private const val REQUEST_CODE = 42
    }

    private lateinit var binding: ActivityNoBinding
    private lateinit var photoFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTakePicture.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getPhotoFile(FILE_NAME)

            val fileProvider =
                FileProvider.getUriForFile(this, "bangkit.android.capstone.fileprovider", photoFile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
            if (takePictureIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_CODE)
            } else {
                Toast.makeText(this, "Unable to open camera", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnextno.setOnClickListener {
            startActivity(Intent(this@NoActivity, YesActivity::class.java).apply {
                putExtra(ProductActivity.EXTRA_STRING, PilihKulitData.listKulit[4].title)
                putExtra(ProductActivity.EXTRA_TIPE_KULIT, 4)
            })
        }
    }

    private fun getPhotoFile(fileName: String): File {
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            binding.imageView.setImageBitmap(takenImage)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}