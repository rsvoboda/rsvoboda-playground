import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {AppComponent, StateComponent, ItemComponent} from './app.component';

@NgModule({
  declarations: [
      AppComponent,
      ItemComponent,
      StateComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
