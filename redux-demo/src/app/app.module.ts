import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {NgRedux, NgReduxModule} from 'ng2-redux';

import { AppComponent } from './app.component';
import { IAppState, rootReducer } from './store';

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
  constructor(ngRedux: NgRedux<IAppState>) {
    ngRedux.configureStore(rootReducer, {})
  }
 }
