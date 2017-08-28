import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {NgRedux, NgReduxModule, DevToolsExtension} from 'ng2-redux';

import { AppComponent } from './app.component';
import { IAppState, INITIAL_STATE, rootReducer } from './store';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    NgReduxModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(ngRedux: NgRedux<IAppState>, private devTools: DevToolsExtension) {
    let enhancers = [];
    if (devTools.isEnabled()) {
      enhancers = [ ...enhancers, devTools.enhancer() ];
    }

    ngRedux.configureStore(rootReducer, INITIAL_STATE, [], enhancers)  // pass initial state of the store
  }
 }
