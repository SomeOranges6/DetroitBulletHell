package main.gameplay;

import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public record Weapon(BufferedImage texture, BiConsumer<Integer, Integer> onShoot){}
