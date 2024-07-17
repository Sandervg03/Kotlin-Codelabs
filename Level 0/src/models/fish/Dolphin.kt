package models.fish

class Dolphin
    (override val name: String = "Dolphin") : Fish(),
    FishAction by PrintingFishAction(food = "Squid", moveMethod = "Swims and jumps out of the water."),
    FishColor by GrayFish
