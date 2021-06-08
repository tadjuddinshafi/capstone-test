import tensorflow as tf
import numpy as np
import os, json
from flask import Flask, request
from google.cloud import storage
from tensorflow.keras.preprocessing import image
from tensorflow.keras.models import load_model

app = Flask(__name__)

@app.route('/', methods=["GET"])
def index():
    data = {"status": 200, "data": "Hello world"}

model = load_model('skincare.h5')
model.summary()

storage_client = storage.Client()
path = storage_client.get_bucket('facethetic-bucket-2')
img = image.load_img(path, target_size=(150, 150))
x = image.img_to_array(img)
x = np.expand_dims(x, axis=0)
images = np.vstack([x])
classes = model.predict(images, batch_size=10)
print(classes.argmax())
print(path)
print(classes)

# def pred_skin_(img_):
#     img = image.load_img(img_, target_size=(150, 150))
#     x = image.img_to_array(img)
#     x = np.expand_dims(x, axis=0)
#     images = np.vstack([x])
#     classes = model.predict(images, batch_size=10)
#     return classes.argmax()

# # test
# pred_skin_('sens.jpeg')

model_tl = load_model('skin_model_5.h5')
# model_tl.summary()

def pred_skin(img_):
    '''
    Fungsi untuk prediksinya
    
    Parameter:
        img_: gambarnya
        
    Output:
        0: dry
        1: komb
        2: normal
        3: oily
        4: sens
    '''
    img = image.load_img(img_, target_size=(150, 150))
    x = image.img_to_array(img)
    x/=255.
    x = np.expand_dims(x, axis=0)
    images = np.vstack([x])
    classes = model_tl.predict(images, batch_size=10)
    return classes.argmax()

# test
pred_skin('sens.jpeg')

if __name__ == '__main__':
    # app.run()
    app.run(host="0.0.0.0", port=int(os.environ.get("PORT", 5000)))