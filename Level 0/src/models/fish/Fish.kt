package models.fish

abstract class Fish {

    abstract val name: String;
    abstract val color: String;

}

interface FishAction {

    fun eat(): Boolean;
    fun swim(): Boolean;

}

class PrintingFishAction(val food: String = "Fish", val moveMethod: String = "Swims") : FishAction {
    override fun eat(): Boolean {
        println("eats $food");
        return true;
    }

    override fun swim(): Boolean {
        println(moveMethod);
        return true;
    }
}