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
package org.mini2Dx.core.game;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Base class for mini2Dx game containers. All games using mini2Dx must extend
 * this.
 */
public abstract class GameContainer {
	public static final float MAXIMUM_DELTA = (1f / 60f);
	
	private float accumulator = 0f;
	private float targetDelta = 0.01f;
	protected int width, height;
	protected Graphics graphics;
	protected SpriteBatch spriteBatch;
	protected ShapeRenderer shapeRenderer;
	private boolean isInitialised = false;
	
	/**
	 * Initialse the game
	 */
	public abstract void initialise();

	/**
	 * Update the game
	 * @param delta The time in seconds since the last update
	 */
	public abstract void update(float delta);
	
	/**
	 * Interpolate the game state
	 * @param alpha The alpha value to use during interpolation
	 */
	public abstract void interpolate(float alpha);

	/**
	 * Render the game
	 * @param g The {@link Graphics} context available for rendering
	 */
	public abstract void render(Graphics g);
	
	
	/**
	 * Called when the game window changes dimensions. 
	 * On mobile devices this is called when the screen is rotated.
	 * 
	 * @param width The new game window width
	 * @param height The new game window height
	 */
	public abstract void onResize(int width, int height);
	
	
	public abstract void onPause();
	
	public abstract void onResume();

	public void render() {
		graphics.preRender(width, height);
		render(graphics);
		graphics.postRender();
	}
	
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		onResize(width, height);
	}
	
	/**
	 * Creates a LibGDX using the internal {@link Graphics} context
	 * @param viewport The {@link Viewport} to be applied
	 * @return An instance of {@link Stage}
	 */
	public Stage createStage(Viewport viewport) {
		return new Stage(viewport, spriteBatch);
	}
	
	/**
	 * Internal pre-initialisation code
	 */
	protected void preinit() {
		this.spriteBatch = new SpriteBatch();
		this.shapeRenderer = new ShapeRenderer();
		
		this.graphics = new Graphics(spriteBatch, shapeRenderer);
	}
	
	/**
	 * Internal post-initialisation code
	 */
	protected void postinit() {}
	
	public void start() {
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		
		if(!isInitialised) {
			preinit();
			initialise();
			postinit();
			isInitialised = true;
		}
	}
	
	public void dispose() {
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
