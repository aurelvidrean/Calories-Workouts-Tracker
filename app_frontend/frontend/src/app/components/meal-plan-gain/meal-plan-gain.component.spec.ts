import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MealPlanGainComponent } from './meal-plan-gain.component';

describe('MealPlanGainComponent', () => {
  let component: MealPlanGainComponent;
  let fixture: ComponentFixture<MealPlanGainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MealPlanGainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MealPlanGainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
