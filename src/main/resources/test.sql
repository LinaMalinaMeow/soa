select vehicleent0_.id               as id1_0_,
       vehicleent0_.x                as x2_0_,
       vehicleent0_.y                as y3_0_,
       vehicleent0_.creation_date    as creation4_0_,
       vehicleent0_.engine_power     as engine_p5_0_,
       vehicleent0_.fuel_type        as fuel_typ6_0_,
       vehicleent0_.name             as name7_0_,
       vehicleent0_.number_of_wheels as number_o8_0_,
       vehicleent0_.type             as type9_0_
from vehicle vehicleent0_
where vehicleent0_.x = 0
  and vehicleent0_.y = 0
  and vehicleent0_.name=?
order by vehicleent0_.id asc
limit ?
