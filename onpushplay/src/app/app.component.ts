import { Component, EventEmitter, Input, OnDestroy, OnInit, OnChanges, Output, SimpleChanges } from '@angular/core';
import {List} from 'immutable';

@Component({
  selector: 'app-root',
  template: `
    <body>
    <p>Test</p>
    <div style="width: 500px; border: solid 1px black; overflow: scroll;">
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
    </body>
    `
})
export class AppComponent {
  states: List<List<Item>> = List<List<Item>>();

  constructor() {
    this.states = this.states.withMutations(mutable => {
      mutable.push(List<Item>([new Item('A'), new Item('B'), new Item('C')]));
      mutable.push(List<Item>([new Item('D'), new Item('E'), new Item('F')]));
      mutable.push(List<Item>([new Item('G'), new Item('H'), new Item('I')]));
      mutable.push(List<Item>([new Item('J'), new Item('K'), new Item('L')]));
      mutable.push(List<Item>([new Item('M'), new Item('N'), new Item('O')]));
      mutable.push(List<Item>([new Item('P'), new Item('Q'), new Item('R')]));
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
  styles: [``]
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
  styles: [``]
})
export class ItemComponent implements OnDestroy, OnInit, OnChanges {

  ngOnChanges(changes: SimpleChanges): void {
    console.log(`Changed ${this.item.name}`);
    for (let propName in changes) {
      let change = changes[propName];
      let curVal  = JSON.stringify(change.currentValue);
      let prevVal = JSON.stringify(change.previousValue);

      console.log(`   ${propName}: Moving from ${prevVal} to ${curVal}`);
    }
    //console.table(changes)
  }

  ngOnInit(): void {
    console.log(`Init ${this.item.name}`);
  }

  ngOnDestroy(): void {
    console.log(`Destroy ${this.item.name}`);
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
