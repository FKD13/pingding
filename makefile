all:	compile run

compile:
ifndef JAVAFX_11_LIB
	$(error Env var JAVAFX_11_LIB not defined)
endif
	javac --module-path ${JAVAFX_11_LIB} -d out/ $(shell find src/ -name "*.java")
	cp src/sample/config.txt src/sample/Main.fxml src/sample/Style.css out/sample/

run:
	cd out && java --module-path ${JAVAFX_11_LIB} --add-modules=javafx.fxml,javafx.graphics,javafx.controls sample.Main
