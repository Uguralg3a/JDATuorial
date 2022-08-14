package de.germanletscode.jdatutorial.commands.test;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import org.jetbrains.annotations.NotNull;

public class TestCommand extends ListenerAdapter {

    private final JDA jda;

    public TestCommand(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId);

        guild.upsertCommand("test", "Ein test / command").setDefaultPermissions(DefaultMemberPermissions.ENABLED).queue();
    }


    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("test")) {
            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("EmbedBuilder") //Das ist der Title der beim Embed kommt
                            .setDescription("Dies ist ein EmbedBuilder!"); //Das ist die Description bei dem Embedbuilder

            event.replyEmbeds(embedBuilder.build()).queue();
        }
    }
}
