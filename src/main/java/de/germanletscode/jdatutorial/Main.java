package de.germanletscode.jdatutorial;

import de.germanletscode.jdatutorial.commands.test.TestCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Main {

    private final JDA jda;
    String guildId = Config.get("guildid");

    public Main() throws LoginException, InterruptedException {
        JDABuilder builder = JDABuilder.createDefault(Config.get("token"));
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS);
        builder.setLargeThreshold(50);

        builder.setActivity(Activity.playing("Baum")); //Legt fest, was er als aktivitätsstatus stehen hat.

        jda = builder.build();

        jda.awaitReady();
        loadFeatures();
    }

    private void loadFeatures() {
        //hier registrieren wir die slash commands
        new TestCommand(jda, guildId);
    }

    public static void main(String[] args) {
        try {
            new Main();
        } catch (LoginException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        jda.shutdown();
    }

    public JDA getJda() {
        return jda;
    }

}
