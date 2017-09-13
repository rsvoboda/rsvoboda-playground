import { ChangeDetectionStrategy, Component, EventEmitter, Input, OnDestroy, OnInit, OnChanges, Output, SimpleChanges } from '@angular/core';
import {List, Map} from 'immutable';

@Component({
  selector: 'app-root',
  template: `
    <body>
    <p>Test</p>

    <app-headers
    [states]="states"
    [leftOffset]="leftOffset"></app-headers>

    <div style="width: 500px; border: solid 1px black; overflow: scroll;" (scroll)="onScrollX($event)">
      <table>
        <tr style="vertical-align: top">
          <td *ngFor="let state of states ; let i = index " style="width: 150px; min-width: 150px;">
            <app-state [stateIndex]="i" [state]="state"
                       (moveItemEvent)="onMoveItemEvent($event)"
                       (moveStateEvent)="onMoveStateEvent($event)"></app-state>
          </td>
        </tr>
      </table>
    </div>

    <h3>map.entries()</h3>
    <table style="width: 200px">
      <tr *ngFor="let entry of map.entries() ; let i = index">
        <td>{{i}}</td>
        <td>{{entry}}</td>
      </tr>
    </table>
    <h3>map.values()</h3>
    <table style="width: 200px">
      <tr *ngFor="let value of map.values() ; let i = index">
        <td>{{i}}</td>
        <td>{{value}}</td>
      </tr>
    </table>
    <h3>map.keys()</h3>
    <table style="width: 200px">
      <tr *ngFor="let key of map.keys() ; let i = index">
        <td>{{i}}</td>
        <td>{{key}}</td>
        <td>{{map.get(key)}}</td>
      </tr>
    </table>

    <h3>List ... first item</h3>
    <ul>
      <li *ngFor="let state of states ; let i = index">{{state.get(0).name}}</li>
    </ul>

    </body>
    `,
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class AppComponent {
  states: List<List<Item>> = List<List<Item>>();
  map: Map<string, string> = Map<string, string>();
  leftOffset = '0px';

  constructor() {
    this.states = this.states.withMutations(mutable => {
      mutable.push(List<Item>([new Item('A'), new Item('B'), new Item('C')]));
      mutable.push(List<Item>([new Item('D'), new Item('E'), new Item('F')]));
      mutable.push(List<Item>([new Item('G'), new Item('H'), new Item('I')]));
      mutable.push(List<Item>([new Item('J'), new Item('K'), new Item('L')]));
      mutable.push(List<Item>([new Item('M'), new Item('N'), new Item('O')]));
      mutable.push(List<Item>([new Item('P'), new Item('Q'), new Item('R')]));
    });
    this.map = this.map.withMutations(mutable => {
        mutable.set('b', 'B');
        mutable.set('a', 'A');
        mutable.set('y', 'Y');
        mutable.set('x', 'X');
      });
  }

  onMoveItemEvent(event: MoveItemEvent) {
    console.log(`%c Moving item ${event.item.name} from ${event.from} to ${event.to} `, 'font-size:12px; background:Orange;');
    if (event.to >= 0 || event.to < this.states.size) {
      let fromState: List<Item> = this.states.get(event.from);
      const index: number = fromState.indexOf(event.item);
      fromState = fromState.remove(index);

      let toState: List<Item> = this.states.get(event.to);
      toState = toState.insert(0, event.item);

      this.states = this.states.set(event.from, fromState);
      this.states = this.states.set(event.to, toState);
    }

  }

  onMoveStateEvent(event: MoveStateEvent) {
    console.log(`%c Moving state ${event.from} to ${event.to} `, 'font-size:12px; background:SkyBlue;');
    if (event.to >= 0 || event.to < this.states.size) {
      const state: List<Item> = this.states.get(event.from);
      this.states = this.states.remove(event.from);
      this.states = this.states.insert(event.to, state);
    }

  }

  onScrollX(event: Event) {
    const boardLeftOffset: number = event.target['scrollLeft'] * -1;
    this.leftOffset = boardLeftOffset + 'px';
    console.log('Setting offset to ' + this.leftOffset);
  }
}

@Component({
  selector: 'app-state',
  template: `
    <ul style="list-style: none; width: 100%">
      <li style="background-color: aliceblue">
        {{stateIndex}}
        <a href="left" (click)="onLeft($event)">&lt;</a>
        &nbsp;
        <a href="right" (click)="onRight($event)">&gt;</a>
      </li>
      <li style="background-color: aliceblue">{{lastChanged()}}</li>
      <app-item *ngFor="let item of state"
        [stateIndex]="stateIndex"
        [item]="item"
        (moveItemEvent)="onMoveItemEvent($event)">
      </app-item>
    </ul>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class StateComponent {
  @Input()
  stateIndex: number;
  @Input()
  state: List<Item> = List<Item>();
  @Output()
  moveItemEvent: EventEmitter<MoveItemEvent> = new EventEmitter<MoveItemEvent>();
  @Output()
  moveStateEvent: EventEmitter<MoveStateEvent> = new EventEmitter<MoveStateEvent>();

  constructor() {
  }

  onLeft(event: MouseEvent) {
    event.preventDefault();
    this.moveStateEvent.emit(new MoveStateEvent(this.stateIndex, this.stateIndex - 1));
  }

  onRight(event: MouseEvent) {
    event.preventDefault();
    this.moveStateEvent.emit(new MoveStateEvent(this.stateIndex, this.stateIndex + 1));
  }

  onMoveItemEvent(moveItemEvent: MoveItemEvent) {
    this.moveItemEvent.emit(moveItemEvent);
  }

  lastChanged() {
    const date: Date = new Date();
    return date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
  }
}

export class MoveStateEvent {
  constructor(readonly from: number, readonly to: number) {
  }
}

@Component({
  selector: 'app-item',
  template: `
    <li>
      <div style="width: 100%; border: solid 1px black">
        <div style="background-color: pink">
          {{item.name}}
          <a href="left" (click)="onLeft($event)">&lt;</a>
          &nbsp;
          <a href="right" (click)="onRight($event)">&gt;</a>
        </div>
        <div>{{lastChanged()}}</div>
      </div>
    </li>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ItemComponent implements OnDestroy, OnInit, OnChanges {

  ngOnChanges(changes: SimpleChanges): void {
    console.log(`%cChanged ${this.item.name}`, 'background:Pink');
    for (let propName in changes) {
      let change = changes[propName];
      let curVal  = JSON.stringify(change.currentValue);
      let prevVal = JSON.stringify(change.previousValue);

      console.log(`   ${propName}: Moving from ${prevVal} to ${curVal}`);
    }
    //console.table(changes)
  }

  ngOnInit(): void {
    console.log(`%cInit ${this.item.name}`, 'background:LightGreen');
  }

  ngOnDestroy(): void {
    console.log(`%cDestroy ${this.item.name}`, 'background:DarkOrange');
  }

  @Input()
  stateIndex: number;
  @Input()
  item: Item;
  @Output()
  moveItemEvent: EventEmitter<MoveItemEvent> = new EventEmitter<MoveItemEvent>();

  constructor() {
  }

  lastChanged() {
    const date: Date = new Date();
    return date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
  }

  onLeft(event: MouseEvent) {
    event.preventDefault();
    this.moveItemEvent.emit(new MoveItemEvent(this.item, this.stateIndex, this.stateIndex - 1));
  }

  onRight(event: MouseEvent) {
    event.preventDefault();
    this.moveItemEvent.emit(new MoveItemEvent(this.item, this.stateIndex, this.stateIndex + 1));
  }
}

export class MoveItemEvent {
  constructor(readonly item: Item, readonly from: number, readonly to: number) {
  }
}

export class Item {
  readonly name: string;


  constructor(name: string) {
    this.name = name;
  }
}

@Component({
  selector: 'app-headers',
  template: `
    <div style="width: 500px; border: solid 1px black; overflow: hidden ; left: -300px">
      <table [ngStyle]="{left: leftOffset}">
        <tr>
          <td style="padding-left: 200px" [attr.colspan]="states.size">{{lastChanged()}}</td>
        </tr>
        <tr style="vertical-align: top ; overflow: hidden ;  white-space: nowrap">
          <app-header *ngFor="let state of states ; let i = index "
                      [state]="i">
          </app-header>
        </tr>
      </table>
    </div>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class HeadersComponent {
  @Input()
  states: List<Item> = List<Item>();
  private _leftOffset: string;


  @Input()
  set leftOffset(value: string) {
    console.log('Received offset to ' + this._leftOffset);
    this._leftOffset = value;
  }

  get leftOffset(): string {
    return this._leftOffset;
  }

  lastChanged() {
    const date: Date = new Date();
    return date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
  }
}

@Component({
  selector: 'app-header',
  template: `
      <td style="width: 150px; min-width: 150px; text-align: center">
        <div>S-{{state}}</div>
        <div>{{lastChanged()}}</div>
      </td>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class HeaderComponent {
  @Input()
  state: number;


  lastChanged() {
    const date: Date = new Date();
    return date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
  }
}