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

import org.mini2Dx.core.game.ScreenBasedGame;
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

/**
 *
 * @author Thomas Cashman
 */
public class UATApplication extends ScreenBasedGame {
    
    @Override
    public void initialise() {
        addScreen(new UATSelectionScreen());
        addScreen(new BlendingUAT());
        addScreen(new ClippingUAT());
        addScreen(new GeometryUAT());
        addScreen(new GraphicsUAT());
        addScreen(new OrthogonalTiledMapNoCachingUAT());
        addScreen(new OrthogonalTiledMapWithCachingUAT());
        addScreen(new IsometricTiledMapUAT());
        addScreen(new ParticleEffectsUAT());
        addScreen(new ControllerUAT());
        addScreen(new ControllerMapping());
    }

    @Override
    public int getInitialScreenId() {
        return 0;
    }
}
