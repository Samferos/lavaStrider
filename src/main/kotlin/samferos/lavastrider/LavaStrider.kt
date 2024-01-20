package samferos.lavastrider

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import net.minecraft.world.gen.GenerationStep
import org.slf4j.LoggerFactory
import samferos.lavastrider.items.CondensedNetherrack
import samferos.lavastrider.items.NetherCatalystOre

object LavaStrider : ModInitializer {
    private val logger = LoggerFactory.getLogger("lavastrider")

	private val CONDENSED_NETHERRACK = CondensedNetherrack(FabricBlockSettings.create())
	private val CATALYST_ORE = NetherCatalystOre(FabricBlockSettings.create())

	val condensedNetherrackPlacedKey = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier("lavastrider", "condensed_netherrack"))
	val catalystOrePlacedKey = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier("lavastrider", "nether_catalyst_ore"))

	override fun onInitialize() {
		Registry.register(Registries.BLOCK, Identifier("lavastrider", "condensed_netherrack"), CONDENSED_NETHERRACK)
		Registry.register(Registries.ITEM, Identifier("lavastrider", "condensed_netherrack"), BlockItem(CONDENSED_NETHERRACK, FabricItemSettings()))
		Registry.register(Registries.BLOCK, Identifier("lavastrider", "nether_catalyst_ore"), CATALYST_ORE)
		Registry.register(Registries.ITEM, Identifier("lavastrider", "nether_catalyst_ore"), BlockItem(CATALYST_ORE, FabricItemSettings()))
		BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.RAW_GENERATION, condensedNetherrackPlacedKey)
		BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, catalystOrePlacedKey)
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Get ready to dive into lava pools.")
	}
}