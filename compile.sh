# Script compiles all java codes in ./src and places in ./bin

src="./src/*.java"

destination="./src/bin"


# Compile files
javac $src -d $destination

echo "Compilation Complete"
