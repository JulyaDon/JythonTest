from PIL import Image, ImageFilter
def openIm(img):
	#Read image
	im = Image.open(img)
	#Display image
	im.show()
	pass