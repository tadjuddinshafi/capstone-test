import tensorflow as tf
import numpy as np
from tensorflow.keras.preprocessing import image
from tensorflow.keras.models import load_model

model = load_model('skincare.h5')
model.summary()

path = "comb.1.jpg"
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