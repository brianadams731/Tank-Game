package com.tank.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontText {
    BitmapFont font;
    private static GlyphLayout glyphLayout;

    public FontText(int fontSize){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("dot-gothic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        font = generator.generateFont(parameter);

        glyphLayout = new GlyphLayout();
    }

    public void setColor(float r, float g, float b, float a){
        font.setColor(r,g,b,a);
    }

    public void drawText(String text, int x, int y, SpriteBatch batch){
        glyphLayout.setText(this.font,text);
        font.draw(batch,glyphLayout, x, y);
    }

    public void drawTextCentered(String text, int yOffsetFromCenter, SpriteBatch batch){
        glyphLayout.setText(this.font,text);
        font.draw(batch,glyphLayout, (GameConstants.WINDOW_WIDTH/2)-(glyphLayout.width/2), (GameConstants.WINDOW_HEIGHT/2) + yOffsetFromCenter);
    }
}
