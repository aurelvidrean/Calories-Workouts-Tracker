import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EndomoprhComponent } from './endomoprh.component';

describe('EndomoprhComponent', () => {
  let component: EndomoprhComponent;
  let fixture: ComponentFixture<EndomoprhComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EndomoprhComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EndomoprhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
