import numpy as np
import cv2 as cv
from PIL import Image

def main():
	head_cascade = cv.CascadeClassifier('haarcascade_upview(0_13st).xml')

	cap = cv.VideoCapture("Entrance1_1417_280218.mp4")

	sF = 1.7
	minN = 12
	minS = (55, 55)
	maxS = (68, 68)
	cv.namedWindow('img', cv.WINDOW_NORMAL)
	cv.resizeWindow('img', 640, 480)
	while 1:
		ret, img = cap.read()
		gray = cv.cvtColor(img, cv.COLOR_BGR2GRAY)

		# detect heads
		heads = head_cascade.detectMultiScale(
			gray,
			scaleFactor=sF,
			minNeighbors=minN,
			minSize=minS,
			maxSize=maxS,
			flags=cv.CASCADE_SCALE_IMAGE)
		print(heads)
		for (x, y, w, h) in heads:
			cv.rectangle(img, (x, y), (x + w, y + h), (255, 0, 0), 2)

		cv.imshow('img', img)
		k = cv.waitKey(30) & 0xff
		return heads
		if k == 27:
			break

	cap.release()
	cv.destroyAllWindows()
