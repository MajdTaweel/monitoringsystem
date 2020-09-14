export interface IPollutionReadings {
  id?: number;
  co2?: number;
  sound?: number;
  sensingNodeId?: number;
}

export class PollutionReadings implements IPollutionReadings {
  constructor(public id?: number, public co2?: number, public sound?: number, public sensingNodeId?: number) {}
}
