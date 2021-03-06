/**
 * Copyright (c) 2015 See AUTHORS file
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of the mini2Dx nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.mini2Dx.uats.util;

import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.Transition;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;
import org.mini2Dx.uats.BlendingUAT;
import org.mini2Dx.uats.ClippingUAT;
import org.mini2Dx.uats.ControllerMapping;
import org.mini2Dx.uats.ControllerUAT;
import org.mini2Dx.uats.GeometryUAT;
import org.mini2Dx.uats.GraphicsUAT;
import org.mini2Dx.uats.IsometricTiledMapUAT;
import org.mini2Dx.uats.ParticleEffectsUAT;
import org.mini2Dx.uats.OrthogonalTiledMapNoCachingUAT;
import org.mini2Dx.uats.OrthogonalTiledMapWithCachingUAT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

/**
 *
 * @author Thomas Cashman
 */
public class UATSelectionScreen extends BasicGameScreen {
    public static final int SCREEN_ID = 0;
    
    private int previousUat = -1;

    @Override
    public void initialise(GameContainer gc) {
    }

    @Override
    public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager,
            float delta) {
    	switch(Mdx.os) {
        case ANDROID:
        case IOS:
        	updateMobileMenu(gc, screenManager, delta);
        	break;
        default:
        	updateDesktopMenu(screenManager, delta);
        	break;
        }
    }

    @Override
    public void interpolate(GameContainer gc, float alpha) {
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        g.clearBlendFunction();
        g.clearShaderProgram();
        g.removeClip();
        
        switch(Mdx.os) {
        case ANDROID:
        case IOS:
        	renderMobileMenu(gc, g);
        	break;
        default:
        	renderDesktopMenu(gc, g);
        	break;
        }
        
        g.setBackgroundColor(Color.WHITE);
        g.setColor(Color.BLUE);
    }
    
    private void updateDesktopMenu(ScreenManager<? extends GameScreen> screenManager,
            float delta) {
        if(Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
            screenManager.enterGameScreen(ScreenIds.getScreenId(BlendingUAT.class), new FadeOutTransition(), new FadeInTransition());
        } else if(Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
            screenManager.enterGameScreen(ScreenIds.getScreenId(ClippingUAT.class), new FadeOutTransition(), new FadeInTransition());
        } else if(Gdx.input.isKeyJustPressed(Keys.NUM_3)) {
            screenManager.enterGameScreen(ScreenIds.getScreenId(GeometryUAT.class), new FadeOutTransition(), new FadeInTransition());
        } else if(Gdx.input.isKeyJustPressed(Keys.NUM_4)) {
            screenManager.enterGameScreen(ScreenIds.getScreenId(GraphicsUAT.class), new FadeOutTransition(), new FadeInTransition());
        } else if(Gdx.input.isKeyJustPressed(Keys.NUM_5)) {
            screenManager.enterGameScreen(ScreenIds.getScreenId(OrthogonalTiledMapNoCachingUAT.class), new FadeOutTransition(), new FadeInTransition());
        } else if(Gdx.input.isKeyJustPressed(Keys.NUM_6)) {
            screenManager.enterGameScreen(ScreenIds.getScreenId(OrthogonalTiledMapWithCachingUAT.class), new FadeOutTransition(), new FadeInTransition());
        } else if(Gdx.input.isKeyJustPressed(Keys.NUM_7)) {
            screenManager.enterGameScreen(ScreenIds.getScreenId(IsometricTiledMapUAT.class), new FadeOutTransition(), new FadeInTransition());
        } else if(Gdx.input.isKeyJustPressed(Keys.NUM_8)) {
            screenManager.enterGameScreen(ScreenIds.getScreenId(ParticleEffectsUAT.class), new FadeOutTransition(), new FadeInTransition());
        } else if(Gdx.input.isKeyJustPressed(Keys.NUM_9)) {
            screenManager.enterGameScreen(ScreenIds.getScreenId(ControllerUAT.class), new FadeOutTransition(), new FadeInTransition());
        } else if(Gdx.input.isKeyJustPressed(Keys.NUM_0)) {
            screenManager.enterGameScreen(ScreenIds.getScreenId(ControllerMapping.class), new FadeOutTransition(), new FadeInTransition());
        }
    }
    
    private void renderDesktopMenu(GameContainer gc, Graphics g) {
        float lineHeight = g.getFont().getLineHeight();
        g.drawString("Detected platform: " + Mdx.os, 32, 32);
        g.drawString("1. Blending UAT", 32, 64);
        g.drawString("2. Clipping UAT", 32, 64 + lineHeight + 4);
        g.drawString("3. Geometry UAT", 32, 64 + (lineHeight * 2) + 8);
        g.drawString("4. Graphics UAT", 32, 64 + (lineHeight * 3) + 12);
        g.drawString("5. Orthogonal TiledMap (No Caching) UAT", 32, 64 + (lineHeight * 4) + 16);
        g.drawString("6. Orthogonal TiledMap (With Caching) UAT", 32, 64 + (lineHeight * 5) + 20);
        g.drawString("7. Isometric TiledMap (No Caching) UAT", 32, 64 + (lineHeight * 6) + 24);
        g.drawString("8. Particle Effects UAT", 32, 64 + (lineHeight * 7) + 28);
        g.drawString("9. Controller UAT", 32, 64 + (lineHeight * 8) + 32);
        g.drawString("0. Controller Mapping", 32, 64 + (lineHeight * 9) + 36);
    }
    
    private void updateMobileMenu(GameContainer gc, ScreenManager<? extends GameScreen> screenManager,
            float delta) {
    	if(Gdx.input.justTouched()) {
    		previousUat = previousUat < 9 ? previousUat + 1 : 0;
    		screenManager.enterGameScreen(previousUat, new FadeOutTransition(), new FadeInTransition());
    	}
    }
    
    private void renderMobileMenu(GameContainer gc, Graphics g) {
        float lineHeight = g.getFont().getLineHeight();
        g.drawString("Detected platform: " + Mdx.os, 32, 32);
        g.drawString("Touch screen for next UAT", 32, 64);
    }

    @Override
    public void preTransitionIn(Transition transitionIn) {
    }

    @Override
    public void postTransitionIn(Transition transitionIn) {
    }

    @Override
    public void preTransitionOut(Transition transitionOut) {
    }

    @Override
    public void postTransitionOut(Transition transitionOut) {
    }

    @Override
    public int getId() {
        return SCREEN_ID;
    }
}
