import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {AppComponent, HeaderComponent, HeadersComponent, StateComponent, ItemComponent } from './app.component';

@NgModule({
  declarations: [
      AppComponent,
      HeaderComponent,
      HeadersComponent,
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
