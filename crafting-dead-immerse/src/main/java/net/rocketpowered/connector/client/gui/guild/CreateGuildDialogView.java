package net.rocketpowered.connector.client.gui.guild;

import java.util.function.BiConsumer;
import com.craftingdead.immerse.client.gui.screen.Theme;
import com.craftingdead.immerse.client.gui.view.ParentView;
import com.craftingdead.immerse.client.gui.view.TextFieldView;
import com.craftingdead.immerse.client.gui.view.TextView;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.rocketpowered.common.GuildConstants;

public class CreateGuildDialogView extends ParentView {

  public static final Component TITLE = new TranslatableComponent("view.guild.create_guild");

  public CreateGuildDialogView(BiConsumer<String, String> resultConsumer, Runnable cancelListener) {
    super(new Properties<>().styleClasses("dialog").backgroundBlur(50));

    this.addChild(new TextView(new Properties<>())
        .setText(TITLE)
        .setCentered(true));

    var nameFieldView = new TextFieldView(new Properties<>());
    nameFieldView.setPlaceholder(new TextComponent("Name"));
    nameFieldView.setMaxLength(GuildConstants.GUILD_NAME_MAX_LENGTH);
    this.addChild(nameFieldView);

    var tagFieldView = new TextFieldView(new Properties<>());
    tagFieldView.setPlaceholder(new TextComponent("Tag"));
    tagFieldView.setMaxLength(GuildConstants.GUILD_TAG_MAX_LENGTH);
    this.addChild(tagFieldView);

    var controlsView = new ParentView(new Properties<>().id("controls"));
    this.addChild(controlsView);

    var createButtonView = Theme.createGreenButton(new TextComponent("Create"),
        () -> resultConsumer.accept(nameFieldView.getValue(), tagFieldView.getValue()));
    createButtonView.setEnabled(false);
    nameFieldView.setResponder(
        value -> createButtonView.setEnabled(value.length() >= GuildConstants.GUILD_NAME_MIN_LENGTH
            && tagFieldView.getValue().length() >= GuildConstants.GUILD_TAG_MIN_LENGTH));
    tagFieldView.setResponder(
        value -> createButtonView.setEnabled(value.length() >= GuildConstants.GUILD_TAG_MIN_LENGTH
            && nameFieldView.getValue().length() >= GuildConstants.GUILD_NAME_MIN_LENGTH));
    controlsView.addChild(createButtonView);

    controlsView.addChild(Theme.createRedButton(CommonComponents.GUI_CANCEL, cancelListener));
  }
}