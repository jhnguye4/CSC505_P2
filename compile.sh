# Script compiles all java codes in ./src and places in ./src/bin

src="./src/*.java"

destination="./src/bin"


if [[ "$OSTYPE" == "msys" ]]; then
	  class_path=".;./java/lib/gson-2.8.6.jar;src/bin"
  else
	    class_path=".:./java/lib/gson-2.8.6.jar:src/bin"
fi

# Compile files
javac $src -cp $class_path -d $destination

echo "Compilation Complete"
