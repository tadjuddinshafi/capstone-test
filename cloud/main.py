from flask import Flask, render_template, request
import tensorflow as tf
import numpy as np
from tensorflow.keras.preprocessing import image
from tensorflow.keras.models import load_model

from flask_cors import CORS, cross_origin

names = ["dry", "kombinasi", "normal", "oily", "sensitif"]



# Process image and predict label
def processImg(IMG_PATH):
    # Read image
    model = load_model('skincaremobilenet.h5')
    
    # Preprocess image
	img = image.load_img(path, target_size=(150, 150))
	x = image.img_to_array(img)
	x = np.expand_dims(x, axis=0)
	images = np.vstack([x])
	classes = model.predict(images, batch_size=10)
	label = classes.argmax()
	labelName = names[label]
	
    return labelName


# Initializing flask application
app = Flask(__name__)
cors = CORS(app)

@app.route("/")
def main():
    return """
        Application is working
    """

# About page with render template
@app.route("/about")
def postsPage():
    return render_template("about.html")

# Process images
@app.route("/process", methods=["POST"])
def processReq():
    data = request.files["img"]
    data.save("img.jpg")

    resp = processImg("img.jpg")


    return resp


if __name__ == "__main__":
    app.run(debug=True)