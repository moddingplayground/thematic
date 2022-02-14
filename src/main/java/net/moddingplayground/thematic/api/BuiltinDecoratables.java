package net.moddingplayground.thematic.api;

import net.moddingplayground.thematic.api.theme.Decoratable;
import net.moddingplayground.thematic.impl.theme.BuiltinDecoratablesImpl;

public interface BuiltinDecoratables {
    Decoratable LANTERN = BuiltinDecoratablesImpl.LANTERN;
    Decoratable LADDER = BuiltinDecoratablesImpl.LADDER;
    Decoratable BOOKSHELF = BuiltinDecoratablesImpl.BOOKSHELF;
}