package ir.adicom.app.soccerapp.presenters;

import android.support.annotation.NonNull;

import ir.adicom.app.soccerapp.models.Position;
import ir.adicom.app.soccerapp.views.IView;

/**
 *
 * Created by adicom on 6/23/17.
 */

public class Presenter  implements IPresenter {

    @NonNull
    private final IView mView;

    public Presenter(@NonNull final IView view) {
        mView = view;
        Position position = new Position("Forward");
        setGreeting(position.getName());
    }

    @Override
    public void bind() {
    }

    @Override
    public void unBind() {
    }

    private void setGreeting(@NonNull final String greeting) {
        mView.setGreeting(greeting);
    }
}