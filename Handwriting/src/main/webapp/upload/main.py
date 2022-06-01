from model import CTCModel
from transforms import Rescale, Deslant, toRGB, ToTensor, Normalise
from torchvision.transforms import Compose
import torch
from PIL import Image
from learn import Learner

im1 = Image.open("C:/Users/Mali/eclipse-workspace-web/Handwriting/src/main/webapp/upload/input.jpg")
im1 = im1.convert('L')
im1 = im1.point(lambda p: 255 if p > 127 else 0)
im1.save("C:/Users/Mali/eclipse-workspace-web/Handwriting/src/main/webapp/upload/input.png")

dev = "cuda" if torch.cuda.is_available() else "cpu"

model = CTCModel(chan_in=3,  # 3 channel image - imagenet pretrained
                 time_step=96,  # CTC matrix for lines is 96x80
                 feature_size=512,  # conv outputs 512, 32, 1
                 hidden_size=512,
                 output_size=80,  # IAM Lines dataset = 80                               # CTC matrix for lines is 96x80
                 num_rnn_layers=4,  # plus 1 for special blank character
                 rnn_dropout=0)
model.to(dev)

learn = Learner(model=model,
                dataloader=lambda: (None, None),
                decode_map=None)

learn.load(f='C:/Users/Mali/eclipse-workspace-web/Handwriting/src/main/webapp/upload/weights/line/lines.pth',  # file path of weights
           inv_f='C:/Users/Mali/eclipse-workspace-web/Handwriting/src/main/webapp/upload/weights/line/line_decode.pkl',  # path to the decode map
           load_decode=True,  # do we want to replace current decode
           keep_LSTM=True,  # if char_dict is same as IAM can keep
           freeze_conv=False)  # freeze the convolution part

trans = Compose([  # compose sequence of transforms
    Rescale(output_size=(64, 800),  # one key part is that the image
            random_pad=False,  # is rotated 90 degrees (w, h)
            border_pad=(4, 10),
            random_rotation=0,  # random angle rotation (+/-)
            random_stretch=1.0),  # randomly stretch the line (up to)
    toRGB(),  # converted to RGB - imagenet
    ToTensor(rgb=True),  # was 3 channel images
    Normalise(mean=[0.485, 0.456, 0.406],  # pretrained resnet
              std=[0.229, 0.224, 0.225])])
pred = learn.predict(img_path='C:/Users/Mali/eclipse-workspace-web/Handwriting/src/main/webapp/upload/input.png',
                     transforms=trans, show_img=False, dev=dev)
