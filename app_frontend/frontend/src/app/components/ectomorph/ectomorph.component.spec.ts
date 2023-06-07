import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EctomorphComponent } from './ectomorph.component';

describe('EctomorphComponent', () => {
  let component: EctomorphComponent;
  let fixture: ComponentFixture<EctomorphComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EctomorphComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EctomorphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
