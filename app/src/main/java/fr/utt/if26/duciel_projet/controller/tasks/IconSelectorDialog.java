package fr.utt.if26.duciel_projet.controller.tasks;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.maltaisn.icondialog.IconDialog;
import com.maltaisn.icondialog.IconDialogSettings;
import com.maltaisn.icondialog.data.Icon;
import com.maltaisn.icondialog.pack.IconPack;
import com.maltaisn.icondialog.pack.IconPackLoader;
import com.maltaisn.iconpack.defaultpack.IconPackDefault;

import java.util.List;

import fr.utt.if26.duciel_projet.R;
import fr.utt.if26.duciel_projet.controller.DataConsentActivity;

public class IconSelectorDialog implements IconDialog.Callback {
    private IconDialog iconDialog;
    private IconPack iconPack;
    private Context context;
    private Icon selectedIconToAddTask;

    public IconSelectorDialog(Context context) {

        this.iconDialog =
                IconDialog.newInstance(new IconDialogSettings.Builder().build());

        this.context = context;
    }

    public void showDialogIcon(FragmentManager fragmentManager){
        iconDialog.show(fragmentManager, "");

    }

    @Override
    public IconPack getIconDialogIconPack() {
        return getIconPack();
    }


    @Override
    public void onIconDialogCancelled() {}

    public IconPack getIconPack() {
        return iconPack != null ? iconPack : loadIconPack();
    }

    private IconPack loadIconPack() {
        // Create an icon pack loader with application context.
        IconPackLoader loader = new IconPackLoader(this.context);

        // Create an icon pack and load all drawables.
        iconPack = IconPackDefault.createDefaultIconPack(loader);
        iconPack.loadDrawables(loader.getDrawableLoader());

        return iconPack;
    }

    @Override
    public void onIconDialogIconsSelected(@NonNull IconDialog iconDialog, @NonNull List<Icon> list) {

        this.selectedIconToAddTask = list.get(0);
    }


}
