all: tesis.pdf

tesis.pdf: tesis.tex tesis.bib
	pdflatex tesis
	bibtex tesis	
	makeindex tesis
	pdflatex tesis
	pdflatex tesis



