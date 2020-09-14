export interface IMagnetometerReadings {
  id?: number;
  x?: number;
  y?: number;
  z?: number;
  sensingNodeId?: number;
}

export class MagnetometerReadings implements IMagnetometerReadings {
  constructor(public id?: number, public x?: number, public y?: number, public z?: number, public sensingNodeId?: number) {}
}
