package models.aquarium

open class Aquarium(open var height: Int = 10, var width: Int = 20, var length: Int = 10) {

    var valid: Boolean = false;
    open var volume: Int
        get() = width * length * height / 1000;
        set(volume) {
            height = (volume * 1000) / (width * length)
        };
    open val water: Double
        get() = volume * 0.9
    open val shape = "Rectangle";

    init {
        validateDimensions();
    }

    public fun showInformation() {
        println("Height: $height CM, width: $width CM, length: $length CM");
        println("Volume: $volume Liter, water: $water Liter, shape: $shape");
        println("---------------------")
    }

    private fun validateDimensions() {
        if (height < width) {
            println("Correct height and width format");
        } else {
            throw Error("Incorrect height and width format");
        }
    }

}